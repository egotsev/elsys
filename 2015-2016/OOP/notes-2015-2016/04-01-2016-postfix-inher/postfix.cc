#include <iostream>
#include <string>
#include <list>
#include <sstream>

using namespace std;

class CalculationError {
    string message_;

    public:
    
    CalculationError(string msg) : message_(msg) {}
    
    string get_message() {
        return message_;
    }
    
    void print_message() {
        cout << message_ << endl;
    }
};

class Calculator;

class Operation {
    Calculator& calc_;
    const string token_;
    
    public:
    
    Operation(Calculator& calc, string token) : calc_(calc), token_(token) {}
    
    Calculator& get_calc() {
        return calc_;
    }
    
    string get_token() {
        return token_;
    }
    
    virtual void calc() = 0;
};

class Calculator {
    list<double> values;
    list<Operation*> operations;
    
    public:
    
    double get_value() {
        if (values.empty()) {
            throw CalculationError("no values in stack...");
        }
        double value = values.back();
        values.pop_back();
        return value;
    }
    
    void add_value(double value) {
        values.push_back(value);
    }
    
    void add_operation(Operation* operation) {
        operations.push_back(operation);
    }
    
    Operation* get_operation(string token) {
        for (list<Operation*>::iterator it = operations.begin(); it != operations.end(); it++) {
            if ((*it)->get_token() == token) {
                return *it;
            }
        }
        return 0;
    }
    
    void run(istream& in) {
        try {
            while(true) {
                cout << "stack(" << values.size() << "): ";
                string input;
                in >> input;
                if (!in) {
                    break;
                }
                Operation* operation = get_operation(input);
                if (operation) {
                    operation->calc();
                } else {
                    // double?
                    istringstream istr(input);
                    double value;
                    istr >> value;
                    if (!istr) {
                        throw CalculationError("wtf??");
                    }
                    values.push_back(value);
                }
            }
        } catch (CalculationError e) {
            e.print_message();
        }
    }
};

class UnaryOperation: public Operation {
    
    public:

    UnaryOperation(Calculator& calc, string token) : Operation(calc, token) {}

    virtual double eval(double value) = 0;

    virtual void calc() {
        double value = get_calc().get_value();
        double result = eval(value);
        cout << "result: " << result << endl;
        get_calc().add_value(result);
    }
};

class BinaryOperation: public Operation {
    
    public:

    BinaryOperation(Calculator& calc, string token) : Operation(calc, token) {}

    virtual double eval(double value1, double value2) = 0;

    virtual void calc() {
        double value1 = get_calc().get_value();
        double value2 = get_calc().get_value();
        double result = eval(value1, value2);
        cout << "result: " << result << endl;
        get_calc().add_value(result);
    }
};

class Negate: public UnaryOperation {
    public:
    
    Negate(Calculator& calc) : UnaryOperation(calc, "neg") {}
    
    virtual double eval(double value) {
        return -value;
    }
};

class Duplicate: public UnaryOperation {
    public:
    
    Duplicate(Calculator& calc) : UnaryOperation(calc, "dup") {}
    
    double eval(double value) {
        get_calc().add_value(value);
        return value;
    }
};

class Plus: public BinaryOperation {
    public:
    
    Plus(Calculator& calc) : BinaryOperation(calc, "+") {}
    
    double eval(double value1, double value2) {
        return value1 + value2;
    }
};



int main() {
    Calculator c;
    c.add_operation(new Negate(c));
    c.add_operation(new Duplicate(c));
    c.add_operation(new Plus(c));
    c.run(cin);
    return 0;
}

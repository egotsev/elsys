#include <iostream>
#include <climits>
#include <string>

using namespace std;

long gcd(long n, long m) {
    if (n == 0 && m == 0)
        return LONG_MAX;
    else if (n == 0)
        return m;
    else if (m == 0)
        return n;
        
    long gcd = n < m ? n : m;
    while(true) {
        if (n % gcd == 0 && m % gcd == 0) {
            return gcd;
        } else {
            gcd--;  
        }
    }
}

class RationalException {
        string message_;
    public:
        RationalException(string message) {
            message_ = message;
        }
};

class Rational {
    long numerator_;
    long denominator_;
    
    public:
    
    Rational(long n, long d) {
        if (d == 0) {
            throw RationalException("Denominator cannot be 0!");
        }
        numerator_ = n;
        denominator_ = d;
        normalize();
    }
    
    void plus(Rational other) {
        numerator_ = numerator_*other.denominator_ + other.numerator_*denominator_;
        denominator_ *= other.denominator_;
        normalize();
    }
    
    void print() {
        cout << numerator_ << "/" << denominator_ << endl;
    }
    
    void normalize() {
        long gcd_ = gcd(numerator_, denominator_);
        numerator_ /= gcd_;
        denominator_ /= gcd_;

        if (denominator_ < 0) {
            numerator_ *= -1;
            denominator_ *= -1;
        }
    }
};

int main() {
    Rational r1(1, 3);
    Rational r2(2, 3);
    r1.plus(r2);
    r1.print();
    Rational r3(3, 5);
    r2.plus(r3);
    r2.print();
    
    Rational r4(-12, 15);
    r3.plus(r4);
    r3.print();

    Rational r5(12, 0);
    
    return 0;
}












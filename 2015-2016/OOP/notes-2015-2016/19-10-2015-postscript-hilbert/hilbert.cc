#include <vector>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

string to_string(int a) {
    ostringstream s;
    s << a;
    return s.str();
}
    

class Point {
    int x_, y_;

    public:
        
    Point(int x, int y) : x_(x), y_(y) {}
    
    int get_x() {
        return x_;
    }
    
    int get_y() {
        return y_;
    }
    
    string get_str() {
        return to_string(x_) + " " + to_string(y_);
    }
};

class HilbertCurve {
    vector<Point> curve_;
    
    void add_point(int x, int y) {
        curve_.push_back(Point(x, y));
    }
    
    public:
    
    HilbertCurve(int n) {
        curve_ = build_curve(n);
    }
    
    vector<Point> build_curve(int n) {
        vector<Point> temp_curve;
        if (n == 1) {
            temp_curve.push_back(Point(200, 400));
            temp_curve.push_back(Point(200, 200));
            temp_curve.push_back(Point(400, 200));
            temp_curve.push_back(Point(400, 400));
        } else {
            vector<Point> prev_curve = build_curve(n - 1);
            //????
        }
        return temp_curve;
    }
    
    void draw() {
        cout << "newpath" << endl;
        cout << curve_[0].get_str() << " moveto" << endl;
        for(int i = 1; i < curve_.size(); i++) {
            cout << curve_[i].get_str() << " lineto" << endl;
        }
        cout << "stroke" << endl;
    }

};

int main() {
    HilbertCurve hc(1);
    hc.draw();
    return 0;
}















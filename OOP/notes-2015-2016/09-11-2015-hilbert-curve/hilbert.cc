#include <vector>
#include <iostream>
#include <sstream>
#include <string>
#include <cmath>

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
    
    Point move_to(int dx, int dy) {
        return Point(x_ + dx, y_ + dy);
    }
    
    string get_str() {
        return to_string(x_) + " " + to_string(y_);
    }
};

void merge(vector<Point> & v1, vector<Point> & v2) {
    v1.insert(v1.end(), v2.begin(), v2.end());
}

class HilbertCurve {
    vector<Point> curve_;
    int line_;
    
    void add_point(int x, int y) {
        curve_.push_back(Point(x, y));
    }
    
    public:
    
    HilbertCurve(int n, int width) {
        line_ = width / (pow(2, n) - 1);
        curve_ = hilbert_up(Point(5, width + 5), n);
    }

    vector<Point> hilbert_up(Point start, int level) {
        vector<Point> result;
        if (level == 0) {
            result.push_back(start);
            return result;
        }
        vector<Point> tmp = hilbert_left(start, level - 1);
        merge(result, tmp);
        Point last = result.back();
        tmp = hilbert_up(last.move_to(0, -line_), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_up(last.move_to(line_, 0), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_right(last.move_to(0, line_), level-1);
        merge(result, tmp);
        return result;
    }
    
vector<Point> hilbert_left(Point start, int level) {
        vector<Point> result;
        if (level == 0) {
            result.push_back(start);
            return result;
        }
        vector<Point> tmp = hilbert_up(start, level - 1);
        merge(result, tmp);
        Point last = result.back();
        tmp = hilbert_left(last.move_to(line_, 0), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_left(last.move_to(0, -line_), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_down(last.move_to(-line_, 0), level-1);
        merge(result, tmp);
        return result;
    }
    
vector<Point> hilbert_down(Point start, int level) {
        vector<Point> result;
        if (level == 0) {
            result.push_back(start);
            return result;
        }
        vector<Point> tmp = hilbert_right(start, level - 1);
        merge(result, tmp);
        Point last = result.back();
        tmp = hilbert_down(last.move_to(0, line_), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_down(last.move_to(-line_, 0), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_left(last.move_to(0, -line_), level-1);
        merge(result, tmp);
        return result;
    }

vector<Point> hilbert_right(Point start, int level) {
        vector<Point> result;
        if (level == 0) {
            result.push_back(start);
            return result;
        }
        vector<Point> tmp = hilbert_down(start, level - 1);
        merge(result, tmp);
        Point last = result.back();
        tmp = hilbert_right(last.move_to(-line_, 0), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_right(last.move_to(0, line_), level-1);
        merge(result, tmp);
        last = result.back();
        tmp = hilbert_up(last.move_to(line_, 0), level-1);
        merge(result, tmp);
        return result;
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
    int level;
    cin >> level;
    HilbertCurve hc(level, 600);
    hc.draw();
    return 0;
}















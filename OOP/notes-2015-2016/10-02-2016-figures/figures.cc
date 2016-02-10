/*
    Homework:
    Implement the following figures:
    1. Ellipse
    2. Rectangle
    3. Line
    4. Polygon
    5. Polyline
    6. Path
    Deadline: 16.02.2016 17:00
    Place: https://github.com/egotsev/elsys-homeworks/11a/{number_in_class}/01
*/

#include <iostream>
#include <list>

using namespace std;

class Point {
    int x_, y_;
    
    public:
    
    Point(int x=0, int y=0) : x_(x), y_(y) {}
    
    int get_x() const {
        return x_;
    }
    
    int get_y() const {
        return y_;
    }
};

class Shape {
    public:
    
    virtual void draw() const=0;
};

class Circle : public Shape {
    int radius_;
    Point center_;
    
    public:
    
    Circle(Point center, int radius): center_(center), radius_(radius) {}
    
    void draw() const {
        cout << "<circle cx=\"" << center_.get_x()
            << "\" cy=\"" << center_.get_y()
            << "\" r=\"" << radius_ << "\" />"
            << endl;
    }
};

class CompositeFigure : public Shape {
    list<Shape*> content_;
    
    public:
    
    ~CompositeFigure() {
        for (list<Shape*>::iterator it = content_.begin(); it != content_.end(); it++) {
            delete *it;
        }
    }

    void add(Shape* shape) {
        content_.push_back(shape);
    }
    
    void draw() const {
        for (list<Shape*>::const_iterator it = content_.begin(); it != content_.end(); it++) {
            (*it)->draw();
        }
    }
};

class Canvas : public CompositeFigure {
    
    int width_, height_;
    
    public:
    
    Canvas(int width=100, int height=100) : width_(width), height_(height) {}
    
    void draw() const {
        cout << "<svg width=\"" << width_ 
            << "\" height=\"" << height_
            << "\">" << endl;
        CompositeFigure::draw();
        cout << "</svg>" << endl;
    }
    
};


int main() {
    Canvas c(400, 600);
    c.add(new Circle(Point(20, 20), 15));
    c.add(new Circle(Point(50, 100), 50));
    c.draw();
    return 0;
}

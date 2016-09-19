#include <iostream>
#include <list>

using namespace std;

class Style {

};

class Styleable {
    Style style_;
    
    public:
    
    Styleable() : style_(Style()) {}
    
    void set_style(Style style) {
        style_ = style;
    }
    
    Style get_style() {
        return style_;
    }
    
};

class Drawable {
    public:
    
    virtual void draw() const = 0;
};

class Shape : public Drawable, public Styleable {

    public:

    Shape() {
        Styleable();
    }
};

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

class Circle : public Shape {
    
    Point center_;
    int radius_;
    
    public:
    
    Circle(Point center, int radius) : center_(center), radius_(radius) {}
    
    void draw() const {
        cout << "<circle cx=\"" <<  center_.get_x() << "\" cy=\"" << center_.get_y() << "\" r=\"" << radius_ << "\" />" << endl;
    }
};

class CompositeShape : public Shape {

};

class Canvas : public Drawable {
    
    list<Shape*> shapes_;
    
    int width_, height_;
    
    public:
    
    Canvas(int width=100, int height=100) : width_(width), height_(height) {}
    
    void add(Shape* shape) {
        shapes_.push_back(shape);
    }
    
    void draw() const {
        cout << "<svg height=\"" << height_ << "\" width=\"" << width_ << "\">" << endl;
        for(list<Shape*>::const_iterator it = shapes_.begin(); it != shapes_.end(); it++) {
            (*it)->draw();
        }
        cout << "</svg>" << endl;
    }
};

int main() {
    Canvas canvas;
    canvas.add(new Circle(Point(30, 30), 10));
    canvas.add(new Circle(Point(60, 30), 15));
    canvas.draw();
    return 0;
}

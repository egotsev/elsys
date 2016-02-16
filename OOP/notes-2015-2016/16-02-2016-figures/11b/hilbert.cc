/*
    Homework:
    
    Visualize maze with already implemented figures (or you can implement new figures if you need them).
    
    Submit here via pull request: https://github.com/egotsev/elsys-homeworks/11b/{number_in_class}/02/
    
    Deadline: 22.02.2016 17:00
*/

#include <iostream>
#include "figures.cc"

using namespace std;

string int_to_string(int i) {
    ostringstream stream;
    stream << i;
    return stream.str();
}

class HilbertCurve {
    Path path_;
    int height_, level_;

    public:
    
    HilbertCurve(int height=10, int level=1) : height_(height), level_(level) {
        path_.set_style("fill: none");
        a(level_, height_);
    }
    
    void draw() const {
        Drawing drawing(400, 600);
        drawing.add(new Path(path_));
        drawing.draw();
    }

    void a(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    d(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    a(i-1, h);
	    path_.add("l0 " + int_to_string(h));
	    a(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    b(i-1, h);
    }

    void b(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    c(i-1, h);
	    path_.add("l0 " + int_to_string(h));
	    b(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    b(i-1, h);
	    path_.add("l0 " + int_to_string(h));
	    a(i-1, h);
    }

    void c(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    b(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    c(i-1, h);
	    path_.add("l0 " + int_to_string(h));
	    c(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    d(i-1, h);
    }

    void d(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    a(i-1, h);
	    path_.add("l0 " + int_to_string(h));
	    d(i-1, h);
	    path_.add("l" + int_to_string(h) + " 0");
	    d(i-1, h);
        path_.add("l0 " + int_to_string(h));
	    c(i-1, h);
    }

};

int main() {
	
	HilbertCurve hilbert(20, 3);
	hilbert.draw();
	
	return 0;
}


/*
    Draw maze with some figure.   
    Deadline: 23.02.2016 17:00
    Place: https://github.com/egotsev/elsys-homeworks/11a/{number_in_class}/02/
*/

#include <iostream>
#include "figures.cc"
using namespace std;

class HilbertCurve : public Drawable {
    
    int line_, order_;
    Path path;
    
    
    public:
    
    HilbertCurve(int line, int order) : line_(line), order_(order) {
        path.add(Path::Option("M", 600));
	    path.add(Path::Option("", 600));
        a(order_, line_);
        path.set_property("stroke", "purple").set_property("fill", "none").set_property("stroke-width", "2");
    }
    
    void draw() const {
        Canvas canvas(600, 600);
        canvas.add(new Path(path));
        canvas.draw();
    }
    
    void a(int i, int h) {

	    if(i<=0) {
		    return;
	    }
	    d(i-1, h);
	    path.add(Path::Option("l", -h));
	    path.add(Path::Option("", 0));
	    a(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", -h));
	    a(i-1, h);
	    path.add(Path::Option("l", h));
	    path.add(Path::Option("", 0));
	    b(i-1, h);
	
    }

    void b(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    c(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", h));
   	    b(i-1, h);
        path.add(Path::Option("l", h));
	    path.add(Path::Option("", 0));
	    b(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", -h));
	    a(i-1, h);
    }

    void c(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    b(i-1, h);
	    path.add(Path::Option("l", h));
	    path.add(Path::Option("", 0));
	    c(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", h));
	    c(i-1, h);
	    path.add(Path::Option("l", -h));
	    path.add(Path::Option("", 0));
	    d(i-1, h);
    }

    void d(int i, int h) {
	    if(i<=0) {
		    return;
	    }
	    a(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", -h));
	    d(i-1, h);
	    path.add(Path::Option("l", -h));
	    path.add(Path::Option("", 0));
	    d(i-1, h);
	    path.add(Path::Option("l", 0));
	    path.add(Path::Option("", h));
	    c(i-1, h);
    }

};
    


int main() {
	HilbertCurve curve(20, 5);
	curve.draw();
	return 0;
}


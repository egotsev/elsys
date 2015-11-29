#include <iostream>
#include <vector>

using namespace std;

enum Direction {
    UP = 1,
    RIGHT = 1 << 1,
    DOWN = 1 << 2,
    LEFT = 1 << 3
};

class Cell {
    friend class Board;

    int row_, col_;
    bool visited_;
    int walls_;
    Cell* up;
    Cell* right;
    Cell* down;
    Cell* left;
    
    void set_up(Cell* c) {
        up = c;
    }
    
    void set_down(Cell* c) {
        down = c;
    }
    
    void set_right(Cell* c) {
        right = c;
    }
    
    void set_left(Cell* c) {
        left = c;
    }
    
    public:
    
    Cell(int row, int col) : visited_(false), row_(row), col_(col), walls_(UP | RIGHT | DOWN | LEFT) {}
    
    void drill(Direction dir) {
        //dir = 1000
        //~dir = 0111
        //walls_ = 1111
        //wall_ & ~dir = 0111
        walls_ &= ~dir;
    }
    
    bool has_wall(Direction dir) const {
        // walls_ = 1011
        // dir =    1000 => 1000
        // dir =    0010 => 0010
        // dir =    0100 => 0000
        return walls_ & dir;
    }
    
    void draw(int length) const {
        cout << row_*length << " " << col_*length << " moveto" << endl;
        cout << length << " " << 0 << (has_wall(DOWN) ? " rlineto" : " rmoveto") << endl;
        cout << 0 << " " << length << (has_wall(RIGHT) ? " rlineto" : " rmoveto")  << endl;
        cout << -length << " " << 0 << (has_wall(UP) ? " rlineto" : " rmoveto") << endl;
        cout << 0 << " " << -length << (has_wall(LEFT) ? " rlineto" : " rmoveto") << endl;
    }
};

class Board {
    vector<Cell> cells_;
    int rows_, cols_;
    
    public:
    
    Board(int rows, int cols) : rows_(rows), cols_(cols) {
        for (int i = 0; i < rows_; i++) {
            for(int j = 0; j < cols_; j++) {
                cells_.push_back(Cell(i, j));
            }
        }
        
        for (int i = 0; i < rows_; i++) {
            for(int j = 0; j < cols_; j++) {
                Cell& c = at(i, j);
                if (i < rows_ - 1)
                    c.set_up(&at(i + 1,j));
                if (i > 0)
                    c.set_down(&at(i - 1, j));
                if (j < cols_ - 1)
                    c.set_right(&at(i, j + 1));
                if (j > 0)
                    c.set_left(&at(i, j - 1));
            }
        }
    }
    
    Cell& at(int i, int j) {
        return cells_[i * cols_ + j];
    }
    
    void draw(int size = 10) const {
		cout << "newpath" << endl;

		for(vector<Cell>::const_iterator it = cells_.begin();
			it!=cells_.end(); ++it) {
		
			(*it).draw(size);	
		}
		
		cout << "stroke" << endl;
		cout << "showpage" << endl;
		
	}
};


int main() {
    Board b(30, 30);
    b.draw(10);
    return 0;
}

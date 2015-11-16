#include <iostream>
#include <vector>

using namespace std;

class Board {
    int cells_[8];
    
    public:
    
    void queens(int col) {
        if (col == 8) {
            cout << "solution found: " << endl;
            print_solution();
            return;
        }
        for (int row = 0; row < 8; row++) {
            if (!is_beaten(col, row)) {
                cells_[col] = row;
                queens(col + 1);
            }
        }
    }
    
    void queens_iter() {
        vector<int> progress;
        
        int row = 0, col = 0;
        
        while(col >= 0 && col < 8) {
            if (row == 8) {
                row = progress.back();
                progress.pop_back();
                row++;
                col--;
                continue;
            }
            if (is_beaten(col, row)) {
                row++;
            } else {
                cells_[col] = row;
                if (col == 7) {
                    cout << "solution found: " << endl;
                    print_solution();
                    row++;
                } else {
                    progress.push_back(row);
                    col++;
                    row = 0;
                }
            }
        }
    }
    
    bool is_beaten(int col, int row) {
        for(int i = 0; i < col; i++) {
            if (cells_[i] == row) {
                return true;
            }
            if (i - cells_[i] == col - row) {
                return true;
            }
            if (i + cells_[i] == col + row) {
                return true;
            }
        }
        return false;
    }
    
    void print_solution() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col<8; col++) {
                if (cells_[col] == row)
                    cout << " * ";
                else
                    cout << " o ";
            }
            cout << endl;
        }
    }
};

int main() {
    Board board;
    //board.queens(0);
    board.queens_iter();
    return 0;
}

#include <iostream>
#include <cstring>
#include <ostream>

using namespace std;
class IndexOutOfBoundsError {};

class String {
    char* buffer_;
    int size_;
    int capacity_;
    static const int CHUNK_ = 10;

    friend ostream& operator<<(ostream&, String&);

    void ensure_capacity(int resize_length) {
        if (size_ + resize_length > capacity_) {
            resize(resize_length + CHUNK_);
        }
    }

    public:
    
    String(const char* buffer) {
        size_ = strlen(buffer);
        capacity_ = size_ + CHUNK_;
        buffer_ = new char[capacity_];
        strcpy(buffer_, buffer);
    }
    
    String(const String& other) {
        size_ = other.size_;
        capacity_ = other.capacity_;
        buffer_ = new char[size_];
        strcpy(buffer_, other.buffer_);
    }
    
    String& operator=(const String& other) {
        if(this != &other) {
            size_ = other.size_;
            capacity_ = other.capacity_;
            buffer_ = new char[size_];
            strcpy(buffer_, other.buffer_);
        }
        return *this;
    }
    
    ~String() {
        delete [] buffer_;
    }
    
    int size() {
        return size_;
    }
    
    int capacity() {
        return capacity_;
    }
    
    void resize(int resize_length) {
        char* temp = buffer_;
        capacity_ = size_ + resize_length;
        buffer_ = new char[capacity_];
        strcpy(buffer_, temp);
        delete [] temp;
    }
    
    void insert(int pos, String add) {
        if (pos < 0 || pos > size_) {
            throw IndexOutOfBoundsError();
        }
        int added_size = add.size();
        ensure_capacity(added_size);
        char* temp = new char[size_ - pos];
        strcpy(temp, &buffer_[pos]);
        strcpy(&buffer_[pos], add.buffer_);
        strcpy(&buffer_[pos + added_size], temp);
        size_ += added_size;
        delete [] temp;
        // buffer = "I want to insert"
        // add = "don't "
        // 1. temp = "want to insert"
        // 2. buffer = "I don't"
        // 3. buffer = "I don't want to insert"
    }
    
    char& operator[](unsigned index) {
        if (index >= size_) {
            throw IndexOutOfBoundsError();
        }
        return buffer_[index];
    }
    
    String& operator+(const String& other) {
        insert(size_, other);
        return *this;
    }
    
    String& meaningless() {
        cout << buffer_ << endl;
        return *this;
    }

};

ostream& operator<<(ostream& out, String& str) {
    out << str.buffer_;
    return out;
}

int main() {
    String str = "I want to insert!";
    str.insert(2, "don't ");
    cout << str << endl;
    
    cout << str[5] << str[6] << str[7] << endl;
    
    
    String str1 = "I ";
    String str2 = "am ";
    String str3 = "concatenated.";
    cout << str1 + str2 + str3 << endl;
}








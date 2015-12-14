#include <iostream>
using namespace std;

class ListError {};

class List {
    class Node {
        friend class List;
        int data_;
        Node* next_;
        Node* prev_;
        
        Node(int data) : data_(data), next_(0), prev_(0) {} 
    };
    
    Node* head_;
    
    public:
    
    List() : head_(new Node(0)) {
        head_->next_ = head_;
        head_->prev_ = head_;
    }
    
    ~List() {
        while(!empty()) {
            pop_back();
        }
        delete head_;
    }
    
    bool empty() {
        return head_->next_ == head_;
    }
    
    void push_back(int value) {
        Node* new_node = new Node(value);
        Node* last = head_->prev_;
        
        new_node->prev_ = last;
        new_node->next_ = head_;
        
        last->next_ = new_node;
        head_->prev_ = new_node;
    }
    
    void push_front(int value) {
        Node *new_node = new Node(value);
        Node *first = head_->next_;
        
        new_node->prev_ = head_;
        new_node->next_ = first;
        
        first->prev_ = new_node;
        head_->next_ = new_node;
    }
    
    int pop_back() {
        if (empty()) {
            throw ListError();
        }
        Node* last = head_->prev_;
        Node* new_last = last->prev_;
        
        new_last->next_ = head_;
        head_->prev_ = new_last;
        
        int result = last->data_;
        delete last;
        return result;
    }
    
    int pop_front() {
        if (empty()) {
            throw ListError();
        }
        Node* first = head_->next_;
        Node* new_first = first->next_;
        
        head_->next_ = new_first;
        new_first->prev_ = head_;
        
        int result = first->data_;
        delete first;    
        return result;
    }
    
    class Iterator {
        friend class List;
        
        Node* ptr_;
        
        Iterator(Node* ptr) : ptr_(ptr) {}
        
        public:
        
        Iterator& next() {
            ptr_ = ptr_->next_;
            return *this;
        }
        
        Iterator& prev() {
            ptr_ = ptr_->prev_;
        }
        
        int& operator*() {
            return ptr_->data_;
        }
        
        bool operator!=(const Iterator& other) {
            return ptr_ != other.ptr_;
        }
        
        //prefix ++
        Iterator& operator++() {
            return next();
        }
        
        //postfix ++
        Iterator operator++(int) {
            Iterator result(*this);
            ptr_ = ptr_->next_;
            return result;
        }
    };
    
    Iterator begin() {
        return Iterator(head_->next_);
    }
    
    Iterator end() {
        return Iterator(head_);
    }
};

int main() {
    List list;
    cout << list.empty() << endl;
    list.push_back(12);
    cout << list.empty() << endl;
    cout << list.pop_back() << endl;
    cout << list.empty() << endl;
    
    
    list.push_back(23);
    list.push_back(34);
    list.push_front(54);
    List::Iterator it = list.begin();
    
    for (List::Iterator it = list.begin(); it != list.end(); it++) {
        cout << *it << endl;
    }

    return 0;
}





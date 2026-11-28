// 01_friend_function.cpp — تابع friend: دسترسی کنترل‌شده به اعضای خصوصی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o friend examples/01_friend_function.cpp

#include <iostream>

class Matrix2x2 {
public:
    Matrix2x2(double a, double b, double c, double d)
        : a_(a), b_(b), c_(c), d_(d) {
    }

    double determinant() const {
        return a_ * d_ - b_ * c_;
    }

    // اعلان دوستی: این تابع به اعضای خصوصی دسترسی دارد
    friend std::ostream& operator<<(std::ostream& out, const Matrix2x2& matrix);

    // تابع friend برای جمع دو ماتریس (دسترسی به هر دو شیء)
    friend Matrix2x2 add(const Matrix2x2& left, const Matrix2x2& right);

private:
    double a_;
    double b_;
    double c_;
    double d_;
};

std::ostream& operator<<(std::ostream& out, const Matrix2x2& matrix) {
    out << "[" << matrix.a_ << " " << matrix.b_ << "]\n";
    out << "[" << matrix.c_ << " " << matrix.d_ << "]";
    return out;
}

Matrix2x2 add(const Matrix2x2& left, const Matrix2x2& right) {
    return Matrix2x2(left.a_ + right.a_, left.b_ + right.b_,
                     left.c_ + right.c_, left.d_ + right.d_);
}

// اگر getter عمومی داشتیم، transpose هم می‌توانست بدون friend کار کند:
//   Matrix2x2 transpose(const Matrix2x2& m) {
//       return Matrix2x2(m.a(), m.c(), m.b(), m.d());
//   }
// یعنی friend تنها راه نیست؛ یک انتخاب طراحی است.

int main() {
    const Matrix2x2 first(1.0, 2.0, 3.0, 4.0);
    const Matrix2x2 second(5.0, 6.0, 7.0, 8.0);

    std::cout << "first =\n" << first << "\n\n";
    std::cout << "second =\n" << second << "\n\n";
    std::cout << "first + second =\n" << add(first, second) << "\n\n";
    std::cout << "det(first) = " << first.determinant() << "\n";

    // دوستی یک‌طرفه است: Matrix2x2 به اعضای خصوصی چیز دیگری دسترسی ندارد
    // و friendها به کلاس ارث نمی‌رسند (زیرکلاس‌ها friend نیستند).

    return 0;
}

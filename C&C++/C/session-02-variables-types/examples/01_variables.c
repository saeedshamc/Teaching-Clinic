/*
 * جلسه ۲ — متغیرها و انواع دادهٔ پایه
 */

#include <stdio.h>

int main(void) {
    /* اعلان و مقداردهی */
    int age = 25;
    char grade = 'A';
    float pi_float = 3.14f;        /* f انتهای عدد یعنی float */
    double pi_double = 3.14159265358979;
    _Bool is_student = 1;          /* 1 یعنی درست */

    /* چاپ با مشخص‌کننده‌های مناسب */
    printf("سن: %d\n", age);               /* %d برای int */
    printf("نمره: %c\n", grade);           /* %c برای char */
    printf("پی اعشاری (float): %f\n", pi_float);
    printf("پی اعشاری (double): %.10f\n", pi_double);
    printf("دانشجو است؟ %d\n", is_student);

    /* تغییر مقدار متغیر */
    age = age + 1;
    printf("سال بعد: %d\n", age);

    return 0;
}

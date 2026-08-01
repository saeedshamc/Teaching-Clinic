/*
 * تمرین ۱ — مساحت مستطیل با double
 * TODO: متغیرهای height و width را تعریف و مقداردهی کنید، مساحت را محاسبه و چاپ کنید.
 */

#include <stdio.h>

int main(void) {
    double height = 5.0;   /* ارتفاع */
    double width = 8.0;    /* عرض */
    double area;           /* مساحت */

    /* TODO: مساحت را محاسبه کنید: area = height * width; */
    /* TODO: با printf مساحت را چاپ کنید */

    /* برای جلوگیری از هشدار unused در حالت ناقص: */
    printf("ارتفاع = %f، عرض = %f (مساحت را خودتان محاسبه کنید)\n", height, width);
    (void)area;

    return 0;
}

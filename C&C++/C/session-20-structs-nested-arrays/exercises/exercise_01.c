/*
 * تمرین ۱ — struct Address داخل struct Person
 * TODO: struct Address (خیابان، شهر، کدپستی) تعریف کنید،
 * داخل Person بگذارید و نمونه‌ای را چاپ کنید.
 */

#include <stdio.h>
#include <string.h>

struct Address {
    char street[100];
    char city[50];
    int postal_code;
};

struct Person {
    char name[50];
    struct Address address;   /* struct تودرتو */
};

int main(void) {
    struct Person p;

    strcpy(p.name, "Ali");
    strcpy(p.address.street, "خیابان آزادی");
    strcpy(p.address.city, "تهران");
    p.address.postal_code = 1234567890;

    printf("نام: %s\n", p.name);
    printf("آدرس: %s، %s، کدپستی %d\n",
           p.address.street, p.address.city, p.address.postal_code);

    return 0;
}

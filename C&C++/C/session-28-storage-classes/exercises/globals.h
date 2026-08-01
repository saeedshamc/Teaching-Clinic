/*
 * تمرین ۲ — هدر برای اعلان متغیر سراسری
 * توجه: اینجا فقط اعلان (extern) است؛ تعریف در globals.c است.
 */

#ifndef GLOBALS_H
#define GLOBALS_H

extern int global_counter;   /* اعلام: متغیر در جای دیگری تعریف شده است */

int increment_counter(void);
int get_counter(void);

#endif /* GLOBALS_H */

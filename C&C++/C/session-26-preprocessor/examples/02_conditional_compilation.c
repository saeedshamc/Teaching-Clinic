/*
 * جلسه ۲۶ — کامپایل شرطی با #ifdef / #ifndef
 * کامپایل با دیباگ:  gcc -DDEBUG -Wall -Wextra -o prog 02_conditional_compilation.c
 */

#include <stdio.h>

/* این پرچم فعال است */
#define FEATURE_LOGGING

int main(void) {
#ifdef DEBUG
    printf("[دیباگ] برنامه شروع شد\n");
#endif

#ifdef FEATURE_LOGGING
    printf("ثبت رویدادها فعال است.\n");
#endif

#ifndef FEATURE_LOGGING
    printf("ثبت رویدادها غیرفعال است.\n");
#endif

    printf("سلام دنیا!\n");
    return 0;
}

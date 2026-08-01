/*
 * تمرین ۲ — چراغ راهنمایی با enum و switch
 */

#include <stdio.h>

enum TrafficLight { RED, YELLOW, GREEN };

void print_message(enum TrafficLight light) {
    switch (light) {
        case RED:
            printf("ایست!\n");
            break;
        case YELLOW:
            printf("آماده شو...\n");
            break;
        case GREEN:
            printf("برو!\n");
            break;
    }
}

int main(void) {
    print_message(GREEN);
    print_message(YELLOW);
    print_message(RED);
    return 0;
}

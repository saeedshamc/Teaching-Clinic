/*
 * جلسه ۲۲ — enum در switch
 */

#include <stdio.h>

enum TrafficLight { RED, YELLOW, GREEN };

const char *light_message(enum TrafficLight light) {
    switch (light) {
        case RED:
            return "ایست!";
        case YELLOW:
            return "آماده شو...";
        case GREEN:
            return "برو!";
        default:
            return "نامعتبر";
    }
}

int main(void) {
    enum TrafficLight current = GREEN;
    printf("%s\n", light_message(current));

    current = RED;
    printf("%s\n", light_message(current));

    return 0;
}

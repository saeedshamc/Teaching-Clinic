/* پیاده‌سازی توابع کمکی تمرین ۲ — جلسه ۴۵ */

#include "utils_ex.h"

int max_of(const int arr[], int n) {
    int m = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > m) {
            m = arr[i];
        }
    }
    return m;
}

int min_of(const int arr[], int n) {
    int m = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] < m) {
            m = arr[i];
        }
    }
    return m;
}

double avg_of(const int arr[], int n) {
    double sum = 0.0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    return sum / n;
}

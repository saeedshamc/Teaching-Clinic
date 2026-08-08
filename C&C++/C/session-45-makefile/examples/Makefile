# Makefile نمونه — جلسه ۴۵
# نکته: خطوط recipe باید با Tab شروع شوند نه فاصله!

CC     = gcc
CFLAGS = -Wall -Wextra -g
TARGET = simple_make

# اهدافی که فایل نیستند
.PHONY: all run clean debug

all: $(TARGET)

# قانون الگویی: هر .c را به .o تبدیل کن
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# ساخت خروجی نهایی
$(TARGET): 01_simple_make.o
	$(CC) $(CFLAGS) -o $(TARGET) 01_simple_make.o

run: $(TARGET)
	./$(TARGET)

debug: $(TARGET)
	gdb ./$(TARGET)

clean:
	rm -f *.o $(TARGET)

# Makefile پروژهٔ نهایی — جلسه ۵۰
# نکته: خطوط recipe باید با Tab شروع شوند

CC     = gcc
CFLAGS = -Wall -Wextra -g

.PHONY: all shell db run-shell run-db clean

all: 01_mini_shell 02_file_db

01_mini_shell: 01_mini_shell.c
	$(CC) $(CFLAGS) -o 01_mini_shell 01_mini_shell.c

02_file_db: 02_file_db.c
	$(CC) $(CFLAGS) -o 02_file_db 02_file_db.c

run-shell: 01_mini_shell
	./01_mini_shell

run-db: 02_file_db
	./02_file_db

clean:
	rm -f 01_mini_shell 02_file_db

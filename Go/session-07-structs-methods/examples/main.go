package main

import (
	"errors"
	"fmt"
)

type Account struct {
	owner   string
	balance int64
}

func NewAccount(owner string) *Account { return &Account{owner: owner} }
func (a *Account) Deposit(amount int64) error {
	if amount <= 0 {
		return errors.New("???? ???? ???? ????")
	}
	a.balance += amount
	return nil
}
func (a Account) Summary() string { return fmt.Sprintf("%s: %d", a.owner, a.balance) }
func main()                       { a := NewAccount("Sara"); _ = a.Deposit(250_000); fmt.Println(a.Summary()) }

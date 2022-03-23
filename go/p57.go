package main

import (
	"fmt"
	"math/big"
)

type Rational struct {
	p, q big.Int
}

func get_nominator(a Rational) *big.Int {
	return new(big.Int).Add(
		new(big.Int).Mul(big.NewInt(2), &a.q),
		&a.p)
}

func add(a Rational) Rational {
	return Rational {
		p: *get_nominator(a),
		q: a.q,
	}
}

func inverse(r Rational) Rational {
	return Rational{
		p: r.q,
		q: r.p,
	}
}

func digits_num(num big.Int) int {
	return len(num.String())
}

func continued_fraction(i int, p_count * int) Rational {
	if i == 0 {
		return Rational{p: *big.NewInt(1), q: *big.NewInt(2)}
	}

	result := inverse(add(continued_fraction(i - 1, p_count)))
	
	if digits_num(*new(big.Int).Add(&result.p, &result.q)) > digits_num(result.q) {
		*p_count++
	}

	return result
}

func main() {
	count := 0;
	continued_fraction(1000, &count)
	fmt.Printf("count: %v\n", count)
}
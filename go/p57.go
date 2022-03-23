package main

import (
	"fmt"
	"math"
	"math/big"
)

type Rational struct {
	p, q big.Int
	// p, q uint64
}

func (r * Rational) print() {
	fmt.Printf("r: %v / %v\n", r.p, r.q)
}

func is_common_deno(a, b Rational) bool {
	zero := new(big.Int)

	return new(big.Int).Mod(&a.q, &b.q).Cmp(zero) == 0 || 
	new(big.Int).Mod(&b.q, &a.q).Cmp(zero) == 0
}

func get_max_deno(a, b Rational) *big.Int {
	return big.NewInt(int64(math.Max(float64(a.q.Uint64()), float64(b.q.Uint64()))))
}

func get_nominator_common(a, b Rational, max_q big.Int) *big.Int {
	return new(big.Int).Add(
		new(big.Int).Mul(&a.p, new(big.Int).Div(&max_q, &a.q)),
		new(big.Int).Mul(&b.p, new(big.Int).Div(&max_q, &b.q)))
}

func get_nominator_not_common(a, b Rational) *big.Int {
	return new(big.Int).Add(
		new(big.Int).Mul(&a.p, &b.q),
		new(big.Int).Mul(&b.p, &a.q))
}

func add(a, b Rational) Rational {
	a.reduce(); b.reduce()
	var result Rational

	switch {
	case is_common_deno(a, b):
		max_q := get_max_deno(a, b)

		result = Rational {
			p: *get_nominator_common(a, b, *max_q),
			q: *max_q,
		}
	default:
		result = Rational {
			p: *get_nominator_not_common(a, b),
			q: *new(big.Int).Mul(&a.q, &b.q),
		}
	}

	result.reduce()

	return result
}

func inverse(r Rational) Rational {
	return Rational{
		p: r.q,
		q: r.p,
	}
}

func gcd(a big.Int, b big.Int) big.Int {
	switch {
	case b.Cmp(big.NewInt(0)) == 0: return a
	default: return gcd(b, *new(big.Int).Mod(&a, &b))
	}
}

func (r * Rational) reduce() {
	var d big.Int

	for {
		d = gcd(r.p, r.q)
		if d.Cmp(big.NewInt(1)) == 0 { break }
		r.p.Div(&r.p, &d)
		r.q.Div(&r.q, &d)
	}
}

func digits_num(num big.Int) uint {
	return uint(math.Floor(math.Log10(float64(num.Uint64())))) + 1
}

func continued_fraction(i int, p_count * int) Rational {
	if i == 0 {
		return Rational{p: *big.NewInt(1), q: *big.NewInt(2)}
	}

	result := inverse(add(Rational{p: *big.NewInt(2), q: *big.NewInt(1)}, continued_fraction(i - 1, p_count)))

	fmt.Println("result:", result)
	
	if digits_num(*new(big.Int).Add(&result.p, &result.q)) > digits_num(result.q) {
		*p_count++
		// fmt.Printf("result: %v\n", p_count);
	}

	// this line is not significant, we just want to count
	return result
}

func main() {
	// count := 0;
	// result := add(Rational{1, 1}, continued_fraction(1000, &count))
	// continued_fraction(999, &count)
	// fmt.Printf("count: %v\n", count)
	// TODO: do some experiments on math.big.Int

	a := big.NewInt(8)
	// b := big.NewInt(6)

	for i:=10; i >= 1; i--{
		fmt.Printf("a.Mul(a, a): %v\n", a.Mul(a, a))
	}
	// // c := new(big.Int)
	// // fmt.Println(c.Mod(a, b))
	// // fmt.Println(c.Div(a, b))
	// // fmt.Println(c.Add(a, b))
	// // fmt.Println(c.Mul(a, b))
	// fmt.Println(new(big.Int).Mod(a, b))
}
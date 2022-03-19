use num_bigint::BigInt;

fn get_digits_sum(num: &BigInt) -> u32 {
    let mut sum = 0;
    let num_str = num.to_str_radix(10);
    let c_digits = num_str.chars();

    for c in c_digits {
        let i_digit = c.to_digit(10).unwrap();
        sum += i_digit;
    }

    return sum;
}

fn powerful_digit_sum() {
    let mut max_digits_sum = 0;

    for a in 2..100 {
        let base = BigInt::from(a);
        let mut mul = BigInt::from(a);
        
        for _b in 1..100 {
            mul = mul * &base;
            
            let digits_sum = get_digits_sum(&mul);

            if digits_sum > max_digits_sum {
                max_digits_sum = digits_sum;
            }
        }
    }

    println!("{}", max_digits_sum);
}

fn main() {
    powerful_digit_sum();
}
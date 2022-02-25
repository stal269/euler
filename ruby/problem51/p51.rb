
require_relative '../utils.rb';

$primes = eSieve(2, 1000000);

$placeholder = "#";

def considerRequestedFamilySize(family, size)
    familyPrimes = [];
    startNumber = family[0] == $placeholder ? 1 : 0;
    
    for i in startNumber..9
        potentialFamilyMember = family.gsub($placeholder, i.to_s).to_i;

        if $primes.include?(potentialFamilyMember)
            familyPrimes.push(potentialFamilyMember);
        end
    end

    # for debugging
    # puts "#{familyPrimes.inspect} #{family}";
    #  

    if familyPrimes.length == size
        puts "#{familyPrimes} #{family}";
    end
end

def getRelevantChars(currentFamily, index, numberOfDigits)
    characters = Array.new(9) {|i| (i + 1).to_s};

    # add 0 to relevant characters if the first place != # and we are not
    # considering MSD
    if currentFamily[0] != $placeholder && index > 0
        characters.unshift("0");
    end

    # if we are not considering LSD we can add # 
    if index < numberOfDigits - 1
        characters.unshift($placeholder);
    end

    # if we are in the index before LSD, and we didn't used # yet, return only [#]
    if index == numberOfDigits - 2 && !currentFamily.include?($placeholder)
        return [$placeholder];
    end

    return characters;
end


def checkIfHavePrimeFamilyHelper(currentFamily, index, numberOfDigits, size)
    if index == numberOfDigits
        considerRequestedFamilySize(currentFamily, size)

        return;
    end

    characters = getRelevantChars(currentFamily, index, numberOfDigits);

    for char in characters
        checkIfHavePrimeFamilyHelper(currentFamily + char, index + 1, numberOfDigits, size);
    end
end

def checkIfHavePrimeFamily(numberOfDigits, size)
    checkIfHavePrimeFamilyHelper("", 0, numberOfDigits, size);
end

def primeDigitReplacement(size)
    for numberOfDigits in 2..10
        checkIfHavePrimeFamily(numberOfDigits, size);
    end
end

primeDigitReplacement(8);

require 'date';
require_relative '../utils.rb';

startTime = Time.now.to_f;

$primes = eSieve(2, 1000000);
$placeholder = "#";

def isRequestedFamilySize(family, size)
    familyPrimes = [];
    startNumber = family[0] == $placeholder ? 1 : 0;
    
    for i in startNumber..9
        potentialFamilyMember = family.gsub($placeholder, i.to_s).to_i;

        if $primes.include?(potentialFamilyMember)
            familyPrimes.push(potentialFamilyMember);
        end
    end

    if familyPrimes.length == size
        puts "#{familyPrimes} #{family}";

        return true;
    end

    return false;
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

    if index == numberOfDigits - 1
        return %w[1 3 7 9];
    end

    return characters;
end


def checkIfHavePrimeFamilyHelper(currentFamily, index, numberOfDigits, size)
    if index == numberOfDigits
        if isRequestedFamilySize(currentFamily, size)
            return true;
        end

        return false;
    end

    characters = getRelevantChars(currentFamily, index, numberOfDigits);
    isFound = false;

    for char in characters
        isFound ||= checkIfHavePrimeFamilyHelper(currentFamily + char, index + 1, numberOfDigits, size);
    end

    return isFound;
end

def checkIfHavePrimeFamily(numberOfDigits, size)
    return checkIfHavePrimeFamilyHelper("", 0, numberOfDigits, size);
end

def primeDigitReplacement(size)
    numberOfDigits = 2;

    for numberOfDigits in 2..6
        if checkIfHavePrimeFamily(numberOfDigits, size)
            break;
        end
    end
end

primeDigitReplacement(8);

endTime = Time.now.to_f;

puts "the program ran for #{(endTime - startTime).round(3)}";
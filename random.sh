#!/bin/sh

# Random number generator script
# Usage: ./random.sh <lower_bound> <upper_bound>
# Outputs a random integer between lower_bound and upper_bound (inclusive)

# Check if exactly two arguments are provided
if [ $# -ne 2 ]; then
    echo "Error: Exactly two arguments required." >&2
    echo "Usage: $0 <lower_bound> <upper_bound>" >&2
    exit 1
fi

lower=$1
upper=$2

# Validate that both arguments are numeric (support negative numbers)
if ! echo "$lower" | grep -qE '^-?[0-9]+$'; then
    echo "Error: Lower bound must be a valid integer." >&2
    exit 1
fi

if ! echo "$upper" | grep -qE '^-?[0-9]+$'; then
    echo "Error: Upper bound must be a valid integer." >&2
    exit 1
fi

# Validate that lower bound is less than or equal to upper bound
if [ "$lower" -gt "$upper" ]; then
    echo "Error: Lower bound must be less than or equal to upper bound." >&2
    exit 1
fi

# Calculate the range
range=$((upper - lower + 1))

# Generate random number between lower and upper (inclusive)
# Using bash's $RANDOM which generates 0-32767
# For ranges larger than 32768, distribution may not be perfectly uniform
random_offset=$((RANDOM % range))
random_number=$((lower + random_offset))

# Output the random number
echo "$random_number"
exit 0

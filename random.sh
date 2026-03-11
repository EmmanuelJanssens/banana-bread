#!/usr/bin/env bash

# random.sh — Print a random integer between two bounds (inclusive).
# Usage: ./random.sh <lower> <upper>

# ── Argument count validation ──────────────────────────────────────────────────
if [[ $# -ne 2 ]]; then
    echo "Error: exactly two arguments required." >&2
    echo "Usage: $(basename "$0") <lower> <upper>" >&2
    exit 1
fi

lower="$1"
upper="$2"

# ── Integer validation (optional leading minus sign, then digits only) ─────────
integer_re='^-?[0-9]+$'

if ! [[ "$lower" =~ $integer_re ]]; then
    echo "Error: lower bound '$lower' is not a valid integer." >&2
    exit 1
fi

if ! [[ "$upper" =~ $integer_re ]]; then
    echo "Error: upper bound '$upper' is not a valid integer." >&2
    exit 1
fi

# ── Bound order validation ─────────────────────────────────────────────────────
if (( lower > upper )); then
    echo "Error: lower bound ($lower) must be less than or equal to upper bound ($upper)." >&2
    exit 1
fi

# ── Random number generation ───────────────────────────────────────────────────
# Uses /dev/urandom to support ranges larger than $RANDOM's 0-32767 ceiling.
range=$(( upper - lower + 1 ))

# Read 4 bytes from /dev/urandom as an unsigned 32-bit integer (max ~4 billion).
rand_bytes=$(od -An -N4 -tu4 /dev/urandom | tr -d ' \n')

result=$(( lower + rand_bytes % range ))

echo "$result"

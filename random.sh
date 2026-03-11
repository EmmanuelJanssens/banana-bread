#!/usr/bin/env bash

# random.sh — Generate a random integer between two user-provided bounds (inclusive).
# Usage: ./random.sh <min> <max>

usage() {
    echo "Usage: $0 <min> <max>"
    echo "  min  Lower bound (integer)"
    echo "  max  Upper bound (integer, must be >= min)"
    echo ""
    echo "Example: $0 1 10"
}

# ── Step 1: Ensure exactly two arguments are provided ────────────────────────
if [[ $# -ne 2 ]]; then
    echo "Error: exactly two arguments required." >&2
    usage >&2
    exit 1
fi

MIN="$1"
MAX="$2"

# ── Step 2: Validate that both arguments are integers ────────────────────────
is_integer() {
    [[ "$1" =~ ^-?[0-9]+$ ]]
}

if ! is_integer "$MIN"; then
    echo "Error: '$MIN' is not a valid integer." >&2
    usage >&2
    exit 1
fi

if ! is_integer "$MAX"; then
    echo "Error: '$MAX' is not a valid integer." >&2
    usage >&2
    exit 1
fi

# ── Step 3: Validate that min <= max ─────────────────────────────────────────
if (( MIN > MAX )); then
    echo "Error: min ($MIN) must not be greater than max ($MAX)." >&2
    usage >&2
    exit 1
fi

# ── Step 4: Generate a random number in [MIN, MAX] ───────────────────────────
# Use shuf when available (handles arbitrarily large ranges).
# shuf -i only accepts non-negative bounds, so we shift the range to start at 0
# and add MIN back to the result.
# Fall back to /dev/urandom, then to $RANDOM for narrow ranges.
RANGE=$(( MAX - MIN + 1 ))

if command -v shuf &>/dev/null; then
    # Shift range to [0, RANGE-1] to satisfy shuf, then add MIN back.
    RESULT=$(( $(shuf -i "0-$(( RANGE - 1 ))" -n 1) + MIN ))
elif [[ -r /dev/urandom ]]; then
    # Read a 4-byte unsigned integer from /dev/urandom and map it into range.
    RAND=$(od -An -N4 -tu4 /dev/urandom | tr -d ' \n')
    RESULT=$(( MIN + RAND % RANGE ))
else
    # $RANDOM yields 0-32767; combine two values for a wider spread.
    RAND=$(( (RANDOM << 15) | RANDOM ))
    RAND=$(( RAND < 0 ? -RAND : RAND ))   # ensure non-negative
    RESULT=$(( MIN + RAND % RANGE ))
fi

# ── Step 5: Output the result ─────────────────────────────────────────────────
echo "$RESULT"

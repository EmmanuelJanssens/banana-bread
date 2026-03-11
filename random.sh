#!/usr/bin/env bash
# random.sh — Generate a random integer between MIN and MAX (inclusive).
# Usage: ./random.sh MIN MAX

set -euo pipefail

# ── 1. Argument count validation ─────────────────────────────────────────────
if [[ $# -ne 2 ]]; then
    echo "Usage: $(basename "$0") MIN MAX" >&2
    echo "  Generates a random integer between MIN and MAX (inclusive)." >&2
    exit 1
fi

MIN="$1"
MAX="$2"

# ── 2. Numeric validation ─────────────────────────────────────────────────────
is_integer() {
    [[ "$1" =~ ^-?[0-9]+$ ]]
}

if ! is_integer "$MIN"; then
    echo "Error: MIN ('$MIN') is not a valid integer." >&2
    exit 1
fi

if ! is_integer "$MAX"; then
    echo "Error: MAX ('$MAX') is not a valid integer." >&2
    exit 1
fi

# ── 3. Range validation ───────────────────────────────────────────────────────
if (( MIN > MAX )); then
    echo "Error: MIN ($MIN) must not be greater than MAX ($MAX)." >&2
    exit 1
fi

# ── 4. Random number generation ───────────────────────────────────────────────
# Strategy: read a large unsigned random integer from /dev/urandom, then map
# it into [MIN, MAX] via modulo arithmetic.  This approach:
#   • handles negative MIN / MAX correctly,
#   • supports arbitrarily large ranges (no 32 767 cap),
#   • works on every POSIX system that has /dev/urandom (Linux, macOS, *BSD).
#
# We read 8 bytes → up to a 64-bit unsigned value so modulo bias is negligible
# for any realistic range.

RANGE=$(( MAX - MIN + 1 ))

# Read 8 random bytes as a hex string, then convert to decimal.
HEX=$(od -An -N8 -tx1 /dev/urandom | tr -d ' \n')
RAND_NUM=$(( 16#$HEX ))

# Guard against negative results from bash signed 64-bit overflow.
if (( RAND_NUM < 0 )); then
    RAND_NUM=$(( -RAND_NUM ))
fi

echo $(( MIN + RAND_NUM % RANGE ))

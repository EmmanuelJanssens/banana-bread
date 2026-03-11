#!/usr/bin/env bash

# random.sh — Output a random integer between two given numbers (inclusive).
# Usage: ./random.sh <min> <max>

# --- Input validation ---

if [[ $# -ne 2 ]]; then
    echo "Usage: $0 <min> <max>" >&2
    exit 1
fi

# Verify that both arguments are valid integers (optional leading minus for negatives)
if ! [[ $1 =~ ^-?[0-9]+$ ]]; then
    echo "Error: '$1' is not a valid integer." >&2
    exit 1
fi

if ! [[ $2 =~ ^-?[0-9]+$ ]]; then
    echo "Error: '$2' is not a valid integer." >&2
    exit 1
fi

min=$1
max=$2

# --- Handle min > max by swapping ---
if (( min > max )); then
    tmp=$min
    min=$max
    max=$tmp
fi

# --- Generate random number in [min, max] ---
# Use /dev/urandom for an unbiased result that works across large ranges.
range=$(( max - min + 1 ))
random_number=$(( min + RANDOM % range ))

# --- Output ---
echo "$random_number"

# **********
# run_tests.py
#
# Wrapper to execute all test cases
#
# **********

# Shared test resources

# Run test suite start-up
import before_tests

# Run each test case
# *****

# Design document exists (not fatal)
import test_design
# Unpack the testing tarball
import test_unpack_tarball
# Tests for the junit test suite
import test_submit_testsuite
import test_compile_testsuite
import test_run_failtests
import test_run_passtest
# Tests for BetterSlot
import test_submit_better
import test_compile_better
import test_rules

# *****
# Run test suite ending (print rubric)
import after_tests

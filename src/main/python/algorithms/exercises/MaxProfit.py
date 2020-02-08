
def max_profit(nums):
    mx = nums[1] - nums[0]
    mn = nums[0]
    for i in range(1, len(nums)):
        mx = max(mx, nums[i] - mn)
        if nums[i] < mn:
            mn = nums[i]
    return mx


print(max_profit([45, 24, 35, 31, 40, 38, 11]))
print(max_profit([20, 15, 10, 8, 2]))

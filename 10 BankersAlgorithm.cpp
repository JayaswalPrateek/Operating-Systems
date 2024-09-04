#include <iostream>
#include <vector>

using namespace std;

vector<int> bankerAlgorithm(int n, int m, const vector<vector<int>>& alloc, vector<vector<int>>& max, vector<int>& avail) {
	vector<int> f(n, 0);  // tracks finished processes
	vector<int> ans;	  // order in which the processes can run safely

	for (int i = 0; i < n; i++) {
		vector<int> need(m);
		for (int j = 0; j < m; j++)
			need[j] = max[i][j] - alloc[i][j];

		bool flag = false;
		for (int j = 0; j < m; j++)
			if (need[j] > avail[j]) {
				flag = true;
				break;
			}

		if (!flag && f[i] == 0) {
			ans.push_back(i);
			f[i] = 1;
			for (int y = 0; y < m; y++)
				avail[y] += alloc[i][y];
		}
	}
	return ans;
}

int main() {
	int n = 5;	// number of processes
	int m = 3;	// number of resource types a, b, c
	vector<vector<int>> alloc = {
		{0, 1, 0},
		{2, 0, 0},
		{3, 0, 2},
		{2, 1, 1},
		{0, 0, 2}};
	vector<vector<int>> max = {
		{7, 5, 3},
		{3, 2, 2},
		{9, 0, 2},
		{2, 2, 2},
		{4, 3, 3}};
	vector<int> avail = {3, 3, 2};

	cout << "Following is the SAFE Sequence:" << endl;
	for (int itemInSafeOrder: bankerAlgorithm(n, m, alloc, max, avail))
		cout << "P" << itemInSafeOrder << " ";
	cout << endl;

	return 0;
}

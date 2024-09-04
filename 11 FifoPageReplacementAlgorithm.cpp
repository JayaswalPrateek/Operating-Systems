#include <iostream>
#include <queue>
#include <unordered_set>

using namespace std;

int FIFO(int pages[], int n, int capacity) {
	queue<int> q;
	unordered_set<int> s;
	int pageFaults = 0;

	for (int i = 0; i < n; ++i)
		if (s.find(pages[i]) == s.end()) {
			if ((int)q.size() < capacity) {
				q.push(pages[i]);
				s.insert(pages[i]);
			} else {
				int removedPage = q.front();
				q.pop();
				s.erase(removedPage);
				q.push(pages[i]);
				s.insert(pages[i]);
			}
			++pageFaults;
		}

	return pageFaults;
}

int main() {
	int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
	int n = sizeof(pages) / sizeof(pages[0]);
	int capacity = 3;  // Number of frames
	int pageFaults = FIFO(pages, n, capacity);
	cout << "Total page faults using FIFO: " << pageFaults << endl;
	return 0;
}

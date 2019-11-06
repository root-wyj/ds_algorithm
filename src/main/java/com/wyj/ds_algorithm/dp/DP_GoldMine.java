package com.wyj.ds_algorithm.dp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>动态规划 之  挖金矿<p>
 * <br> 参考自：http://www.cnblogs.com/sdjl/articles/1274312.html
 * 
 * <br>问题描述：10000个人挖10个金矿，每个金矿需要的人数和产出的金矿是固定的，而且当且仅当用这个多人挖时，产生那么多的金矿，
 * 人多一个少一个 都不能产生任何金矿。而且人挖完一个之后 不会去挖第二个，问题是最多可以挖出多少金子？
 * 
 * <br>解决问题：首先国王来到最后一个金矿，勘探之后，该矿需要1500人，能挖出8888金矿。然后国王叫过来了它的两个大臣-左、右大臣，对
 * 左大臣说，我给你8500人，你告诉我在剩下的9个金矿中，最多能获取多少金矿？对右大臣说，我给你10000人，你告诉我再剩下的9个金矿
 * 中，最多能获取多少金矿？最后国王一比较，就知道 该采取谁的意见了。
 * 
 * <br>同样的大臣也将问题交给自己的手下，通过比较也能得出使用当前的资源，怎么能挖去更多的金矿，并将结果告诉国王。
 * 
 * <br>以上这种问题就可以使用动态规划的思想(注意是思想，而不是算法)来解决。
 * 
 * <p>什么是动态规划</p>
 * <li><b>最优子结构</b>--国王相信，只要两个大臣能给出正确的答案，再加上自己的判断，就一定能够找出最终的正确答案。
 * 这种子问题最优时，母问题通过选择也能达到最优的情况就叫做“最优子结构”</li>
 * <li><b>子问题重叠</b>-- 国王、大臣以及大臣的手下，都是面对的同样的问题，给你一定数量的人和一定数量的金矿，开采出最多的金矿。
 * 其中仅仅是参数不同。这种子问题与母问题本质上是同一个问题的情况叫做“子问题重叠”</li>
 * <li><b>边界</b>-- 如果问题永远划分下去，没有终点，这个问题也会得不到解决。</li>
 * <li><b>子问题独立</b> -- 国王的两个大臣</li>
 * <li><b></b></li>
 * 
 * <p>动态规划的优势与时间复杂度</p>
 * 在本实例中，共有10000人，10个矿，10个矿都可以选择挖或者不挖(两种选择)，那么所有的情况的排列组合就是2的10次方，共1024中选择。
 * 
 * <br>按照上面算法，按照树形结构排列起来，第0个矿也是一共有1024个节点，从第0个矿到底9个矿（从叶节点到根节点）的所有可能也是1024种。
 * 
 * <br>那动态规划有什么优势呢？
 * 
 * <br><b>备忘录！！</b> 将结果记录下来，在之后的查找比对中，如果已经计算，就直接取结果就可以了。其实，如果没有备忘录，动态规划没有任何优势可言。
 * 
 * <br>还是备忘录。比如说之前已经有用户计算过前1个矿，用10000人，能开发多少金子了，那么之后再有人遇到了，就不需要计算了。其他用户可能也会计算8500人，
 * 前一个矿，能开发多少金子。可能又有人计算前3个矿，用7000人需要多少金子。也就是说，最多前一个矿会有10000个不同的结果，前2个矿业最多会有10000个不同的结果，
 * 所以，10个矿，10000个人，最多会有10*10000种数据，但是很明显，并不是每一个问题都会遇到，可能最后只会产生几百个数据。
 * 
 * <br>到这里，动态规划相对于之前的枚举，可能略显优势，那如果把数据扩大到人数依然是100000，矿是100个，枚举所有的可能是2的100次方（这个有点大，我也不说是多少了），
 * 而动态规划最多最多会产生100000*100，而且其中的绝大部分数据都是不会用到的，也就是可能只需要计算上千或者上万中可能。动态规划NB
 * 
 * 
 * @author wuyingjie
 * @date 2018年6月15日
 */

public class DP_GoldMine {
	
	public static final int max_mine = 100;
	private static final int max_people = 1000;
	
	int mine[] = new int[max_mine+1];
	int usePeople[] = new int[max_mine+1];
	
	int table[][] = new int[max_mine+1][max_people+1];
	
	static int hitCount = 0;
	
	int people = 0;
	int mineCount = 0;
	
	public int optimalResolution(int curMine, int curPeople) {
		int result = table[curMine][curPeople];
		
		if (result != 0) {
			hitCount ++;
			return result;
		}
		
		if (curMine == 1) {
			if (curPeople > usePeople[curMine]) {
				result = mine[curMine];
			} else {
				result = 0;
			}
		} else if (curPeople > usePeople[curMine]) {
			//如果当前人数能挖 这个矿，考虑挖或者不挖  那个更多
			result = Math.max(optimalResolution(curMine - 1, curPeople - usePeople[curMine]) + mine[curMine],
					optimalResolution(curMine - 1, curPeople));
		} else {
			// 当前人数 挖不起 这个矿
			result = optimalResolution(curMine - 1, curPeople);
		}
		
		table[curMine][curPeople] = result;
		return result;
	}
	
	public static void main(String[] args) {
		DP_GoldMine goldMine = new DP_GoldMine("src/com/wyj/ds_algorithm/dp/test_data/beibao9.in");
		int result = goldMine.optimalResolution(goldMine.mineCount, goldMine.people);
		System.out.println("最优解："+result);
		System.out.println("重复命中的次数："+hitCount);
	}
	
	public DP_GoldMine(String fileName) {
		File f = new File(fileName);
		if (!f.exists()) throw new RuntimeException("file "+fileName+" not found");
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String[] tmpData = br.readLine().split(" ");
			people = Integer.valueOf(tmpData[0]);
			mineCount = Integer.valueOf(tmpData[1]);
			
			for (int i=1; i<=mineCount; i++) {
				tmpData = br.readLine().split(" ");
				usePeople[i] = Integer.valueOf(tmpData[0]);
				mine[i] = Integer.valueOf(tmpData[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

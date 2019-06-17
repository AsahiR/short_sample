package checker;

import java.util.LinkedList;
import java.util.Queue;

import matrix2017.TCMatrix;

public class TSWAChecker extends TConvergenceChecker {
	// 直近fLim時点分
	int fLim = 10;
	// 過去fLlim時点分の値をいれるキュー
	Queue<Double> fQue;

	@Override
	public void initialize(int dim) {
		super.initialize(dim);
		fQue = new LinkedList<>();
	}

	/**
	 *　算術移動平均
	 * キューに新しい値の格納+最古の値を削除
	 */
	@Override
	public double calcVal() {
		double deltaVal = fDelta.normL2() / Math.sqrt(fDim);
		fQue.add(deltaVal);
		if (fQue.size() > fLim) {
			double leftMost = fQue.element();
			deltaVal -= leftMost;
			fQue.remove();
		}
		// 重みは1.0/fLim
		return fVal + deltaVal / fLim;
	}

	@Override
	public boolean check(TCMatrix now) {
		// 収束判定 + 必要な時点が経過しているか
		return super.check(now) && fQue.size() == fLim;
	}

}

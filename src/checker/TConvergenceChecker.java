package checker;

import matrix2017.TCMatrix;

public abstract class TConvergenceChecker {
	// ベクとrの次元
	int fDim;
	// 差分
	TCMatrix fDelta;
	// 前の値
	TCMatrix fPrev;

	// スカラ評価値
	double fVal;

	// 収束判定の敷居値
	double fMaxSigma;

	/**
	 * 初期化処理
	 * 
	 * @param dim
	 *                ベクトルの次元
	 */
	public void initialize(int dim) {
		fDim = dim;
		fDelta = new TCMatrix(dim, 1);
		fPrev = new TCMatrix(dim, 1);
		fMaxSigma = 0.01;
		fVal = 0;
	}

	// 差分ベクトルからスカラー評価値を計算
	public abstract double calcVal();

	/**
	 * 
	 * @param now
	 *                最新のベクトル
	 * @return 収束したかどうか
	 */
	public boolean check(TCMatrix now) {
		fDelta.sub(now, fPrev);
		fPrev.copyFrom(now);
		//a

		fVal = calcVal();

		// 判定のしきいち
		double eps = 1.0E-1;
		// e-3が加減
		// e-3/e-2あたりSWA.
		return fVal <= eps * fMaxSigma;

	}

}

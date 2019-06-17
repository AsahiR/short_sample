package checker;

public class TEMAChecker extends TConvergenceChecker {
	// 学習率
	double fAlpha = 0.9;

	@Override
	public void initialize(int dim) {
		super.initialize(dim);
		//fAlpha = Param.fProps.getDoubleProperty("EMAAlpha", 0.9);
		fAlpha = 0.9;
	}

	/**
	 * 指数移動平均を計算
	 */
	@Override
	public double calcVal() {
		// 差分のl2ノルムema
		double deltaVal = fDelta.normL2() / Math.sqrt(fDim);
		double ret = fVal * (1.0 - fAlpha) + fAlpha * deltaVal;
		return ret;
	}

}

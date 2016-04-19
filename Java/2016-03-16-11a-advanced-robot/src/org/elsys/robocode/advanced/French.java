package org.elsys.robocode.advanced;

import robocode.AdvancedRobot;
import robocode.GunTurnCompleteCondition;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

public class French extends AdvancedRobot {

	int forward = 1;
	double turnDegrees = randomDegrees();

	@Override
	public void run() {
		while (true) {
			setAhead(forward * 100);
			setTurnLeft(turnDegrees);
			setTurnGunLeft(100);
			waitFor(new GunTurnCompleteCondition(this));
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		if (event.getDistance() < 100) {
			fire(getEnergy() / 10);
		} else {
			fire(1);
		}
		setTurnLeft(event.getBearing());
		waitFor(new TurnCompleteCondition(this));
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		if (event.getBearing() < 0) {
			turnDegrees = Math.random() * 180;
		} else {
			turnDegrees = -Math.random() * 180;
		}

	}

	private double randomDegrees() {
		return Math.random() * 360 - 180;
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		changeDirection();
		turnDegrees = randomDegrees();
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		changeDirection();
	}

	private void changeDirection() {
		forward = -forward;
	}

}

package wlgtext;

import static org.junit.Assert.fail;
import moving.Tech;
import moving.business.RatingMoving;

import org.junit.Test;

public class RatingMovingTest {


	@Test
	public void testMovingH() {
		RatingMoving rm = new RatingMoving(new Tech());
		rm.movingH();
		//fail("Not yet implemented");
	}

	@Test
	public void testMovingL() {
		RatingMoving rm = new RatingMoving(new Tech());
		rm.movingL();
	}

	@Test
	public void testMovingHM() {
		RatingMoving rm = new RatingMoving(new Tech());
		rm.movingHM();
	}

	@Test
	public void testMovingLM() {
		RatingMoving rm = new RatingMoving(new Tech());
		rm.movingLM();
	}

}

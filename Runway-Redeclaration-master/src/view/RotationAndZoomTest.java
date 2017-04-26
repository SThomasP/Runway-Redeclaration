package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RotationAndZoomTest {

	//in these tests the height and width are 0 for some reason therefore it rotates around the point 0,0
	TopViewGUI tvg =  new TopViewGUI();

	@Test
	public void testRotation() {
		
				
		assertEquals(112,tvg.rotateandzoom(Math.PI * 0, 112, 511, 1)[0],1);
		assertEquals(511,tvg.rotateandzoom(Math.PI * 0, 112, 511, 1)[1],1);

		assertEquals(-282,tvg.rotateandzoom(Math.PI * 0.25, 112, 511, 1)[0],1);
		assertEquals(441,tvg.rotateandzoom(Math.PI * 0.25, 112, 511, 1)[1],1);
		
		assertEquals(-511,tvg.rotateandzoom(Math.PI * 0.5, 112, 511, 1)[0],1);
		assertEquals(113,tvg.rotateandzoom(Math.PI * 0.5, 112, 511, 1)[1],1);
		
		assertEquals(-111,tvg.rotateandzoom(Math.PI * 1, 112, 511, 1)[0],1);
		assertEquals(-510,tvg.rotateandzoom(Math.PI * 1, 112, 511, 1)[1],1);
		
	}
	
	@Test
	public void testZoom() {
		
		tvg.setPoint(0, 0);
		assertEquals(224,tvg.rotateandzoom(Math.PI * 0, 112, 511, 2)[0],1);
		assertEquals(1022,tvg.rotateandzoom(Math.PI * 0, 112, 511, 2)[1],1);
		
		
		assertEquals(336,tvg.rotateandzoom(Math.PI * 0, 112, 511, 3)[0],1);
		assertEquals(1533,tvg.rotateandzoom(Math.PI * 0, 112, 511, 3)[1],1);
		
		tvg.setPoint(300, 200);
		assertEquals(-376,tvg.rotateandzoom(Math.PI * 0, 112, 511, 2)[0],1);
		assertEquals(622,tvg.rotateandzoom(Math.PI * 0, 112, 511, 2)[1],1);
	}
	
	@Test
	public void testRotationAndZoom() {
		tvg.setPoint(300, 200);
		
		assertEquals(-1164,tvg.rotateandzoom(Math.PI * 0.25, 112, 511, 2)[0],1);
		assertEquals(481,tvg.rotateandzoom(Math.PI * 0.25, 112, 511, 2)[1],1);
	}

}

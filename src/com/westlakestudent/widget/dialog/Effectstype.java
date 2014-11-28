package com.westlakestudent.widget.dialog;

import com.westlakestudent.widget.effects.BaseEffects;
import com.westlakestudent.widget.effects.FadeIn;
import com.westlakestudent.widget.effects.Fall;
import com.westlakestudent.widget.effects.FlipH;
import com.westlakestudent.widget.effects.FlipV;
import com.westlakestudent.widget.effects.NewsPaper;
import com.westlakestudent.widget.effects.RotateBottom;
import com.westlakestudent.widget.effects.RotateLeft;
import com.westlakestudent.widget.effects.Shake;
import com.westlakestudent.widget.effects.SideFall;
import com.westlakestudent.widget.effects.SlideBottom;
import com.westlakestudent.widget.effects.SlideLeft;
import com.westlakestudent.widget.effects.SlideRight;
import com.westlakestudent.widget.effects.SlideTop;
import com.westlakestudent.widget.effects.Slit;



/*
 * Copyright 2014 litao
 * https://github.com/sd6352051/NiftyDialogEffects
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}

package com.hsicen.a31_generics.shop;

import com.hsicen.a31_generics.fruit.Apple;
import com.hsicen.a31_generics.sim.ChinaUniomSim;

/**
 * 作者：hsicen  2020/2/23 20:10
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class UnicomSimShop implements SimShop<Apple, ChinaUniomSim> {

    @Override
    public ChinaUniomSim buySim(String name, String id) {
        return new ChinaUniomSim();
    }

    @Override
    public Apple buy() {
        return new Apple();
    }

    @Override
    public float refund(Apple item) {
        return item.refound();
    }

}

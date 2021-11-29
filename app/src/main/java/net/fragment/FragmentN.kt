package net.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_f_n.*
import net.basicmodel.R
import net.utils.Constant
import net.utils.NearByUtils

class FragmentN : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.layout_f_n
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView1()
        initView2()
        initView3()
        initView4()
        initView5()
        initView6()
        initView7()
        initView8()
        initView9()
        initView10()
    }

    private fun initView1() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.airport,
            R.mipmap.airport
        )
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.atm, R.mipmap.atm)
        val child3 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.bakery, R.mipmap.bakery)
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView2() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.bank, R.mipmap.bank)
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.bus, R.mipmap.bus)
        val child3 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.cafe, R.mipmap.cafe)
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView3() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.church, R.mipmap.church)
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.cloth, R.mipmap.cloth)
        val child3 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.dentist,
            R.mipmap.dentist
        )
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView4() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.doctor, R.mipmap.doctor)
        val child2 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.fire_station,
            R.mipmap.fire_station
        )
        val child3 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.gas_station,
            R.mipmap.gas_station
        )
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView5() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.hospital,
            R.mipmap.hospital
        )
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.hotel, R.mipmap.hotel)
        val child3 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.jewelry,
            R.mipmap.jewelry
        )
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView6() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.mall, R.mipmap.mall)
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.mosque, R.mipmap.mosque)
        val child3 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.park, R.mipmap.park)
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView7() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.pet, R.mipmap.pet)
        val child2 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.pharmacy,
            R.mipmap.pharmacy
        )
        val child3 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.police, R.mipmap.police)
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView8() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.post_office,
            R.mipmap.post_office
        )
        val child2 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.salon, R.mipmap.salon)
        val child3 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.school, R.mipmap.school)
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView9() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.shoe, R.mipmap.shoe)
        val child2 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.stadium,
            R.mipmap.stadium
        )
        val child3 = NearByUtils.get().createChildView(
            requireActivity(),
            requireActivity(),
            Constant.university,
            R.mipmap.university
        )
        layout1.addView(child1)
        layout1.addView(child2)
        layout1.addView(child3)
        root.addView(layout1)
    }

    private fun initView10() {
        val layout1 = NearByUtils.get().createLinearLayout(requireActivity(), requireActivity())
        val child1 = NearByUtils.get()
            .createChildView(requireActivity(), requireActivity(), Constant.zoo, R.mipmap.zoo)
        layout1.addView(child1)
        root.addView(layout1)
    }

}
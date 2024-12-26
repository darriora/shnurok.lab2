package ru.ssau.tk.shnurok.lab2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ru.ssau.tk.shnurok.lab2.dto.MathFunctionDTO;
import ru.ssau.tk.shnurok.lab2.dto.PointDTO;
import ru.ssau.tk.shnurok.lab2.entity.MathFunctionEntity;
import ru.ssau.tk.shnurok.lab2.entity.PointEntity;
import ru.ssau.tk.shnurok.lab2.exeptions.LoadFunctionExecption;
import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.TabulatedFunction;
import ru.ssau.tk.shnurok.lab2.functions.realizations.ArrayTabulatedFunction;
import ru.ssau.tk.shnurok.lab2.repository.MathFunctionRepository;
import ru.ssau.tk.shnurok.lab2.service.MathFunctionService;
import ru.ssau.tk.shnurok.lab2.service.PointService;

import java.util.List;

@Controller
@RequestMapping("/memory")
public class LoadAndSaveControllers {
    @Autowired
    public MathFunctionRepository mathFunctionsRepository;

    private final MathFunctionService mathFunctionsService;
    private final PointService pointService;

    @Autowired
    public LoadAndSaveControllers(MathFunctionService mathFunctionsService, PointService pointService) {
        this.mathFunctionsService = mathFunctionsService;
        this.pointService = pointService;
    }

    @PostMapping("/load")
    public String load(@RequestParam("target") String target, @RequestParam("id") int id, Model model, HttpSession session) {
        System.out.println("Loading " + target);

        System.out.println(target);

        MathFunctionEntity loadFunc = mathFunctionsRepository.findById(id).orElse(null);

        List<PointEntity> list = loadFunc.getPoints();
        if(list.size() >= 2){
            double[] x = new double[list.size()];
            double[] y = new double[list.size()];

            int i = 0;
            for(PointEntity point : list) {
                System.out.println(point.getId());
                x[i] = point.getXVal();
                System.out.println(point.getXVal());
                y[i] = point.getYVal();
                System.out.println(point.getYVal());
                i++;
            }

            TabulatedFunction result = new ArrayTabulatedFunction(x,y);
            session.setAttribute(target+"Func", result);
            return "redirect:/tabulated-operations";
        }else{
            throw new LoadFunctionExecption("У функции должно быть >2 точек");
        }
    }

    @PostMapping("/save")
    public String save(@RequestParam("target") String saveTarget, @RequestParam("funcName") String funcName, Model model, HttpSession session) {

        TabulatedFunction func = (TabulatedFunction) session.getAttribute(saveTarget+"Func");
        if(func == null) {
            throw new IllegalArgumentException("Function is empty");
        }

        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setFunctionName(funcName);
        dto.setXTo(func.rightBound());
        dto.setXFrom(func.leftBound());
        dto.setCount(func.getCount());

        int idResult = mathFunctionsService.create(dto).getId();

        for(int i = 0; i < func.getCount(); i++) {
            PointDTO point = new PointDTO();
            point.setFunctionId(idResult);
            point.setXVal(func.getX(i));
            point.setYVal(func.getY(i));

            pointService.create(point);
        }

        return "redirect:/tabulated-operations";
    }

}

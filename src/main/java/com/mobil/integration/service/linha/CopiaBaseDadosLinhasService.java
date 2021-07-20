package com.mobil.integration.service.linha;

import com.mobil.integration.model.in.LinhaIn;
import com.mobil.model.entity.Linha;
import com.mobil.model.out.LinhaOut;
import com.mobil.service.linha.SalvaLinhasInService;
import com.mobil.util.converter.LinhasToLinhasOutConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CopiaBaseDadosLinhasService {

    private final BuscaLinhasService buscaLinhasService;
    private final SalvaLinhasInService salvaLinhasInService;
    private final LinhasToLinhasOutConverter linhasToLinhasOutConverter;

    public List<LinhaOut> copiarBaseDados() {
        final List<LinhaIn> linhasIn = buscaLinhasService.buscarLinhas();
        final List<Linha> linhas = salvaLinhasInService.salvarLinhas(linhasIn);

        return linhasToLinhasOutConverter.convert(linhas);
    }
}

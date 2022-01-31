import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.ContasReceberDAO;
import domain.AlterarcoesContrato;
import domain.ContasReceber;


public class scriptReenviarRemessaAlteracaoPlano {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args) {
		
//		4826
//		6229
//		4705
//		8476
//		779
//		6401
//		9759
//		7911
//		3881
//		6820
//		5096
//		7093
//		5130
//		1104
//		598
//		6292
//		1841
//		7228
//		4035
//		7269
//		8469
//		923
//		4733
//		8407
//		8273
//		7224
//		3228
//		7130
//		7311
//		8724
//		9826
//		5123
//		7816
//		8005
//		8212
//		4136
//		8666
//		661
//		4156
//		4673
//		4739
//		7660
//		3372
//		2806
//		7462
//		3713
//		7856
//		3217
//		7697
//		8039
//		7207
//		5017
//		7810
//		6163
//		7187
//		3371
//		384
//		7315
//		8672
//		3751
//		3911
//		6213
//		255
//		7921
//		7951
//		3185
//		6754
//		8527
//		2997
//		3068
//		8086
//		2859
//		8740
//		4782
//		2776
//		6331
//		4967
//		8720
//		7964
//		3020
//		477
//		6260
//		638
//		8711
//		6838
//		8737
//		7913
//		3478
//		8477
//		4376
//		7183
//		3182
//		6265
//		5397
//		8763
//		7282
//		6935
//		7912
//		1095
//		8374
//		7181
//		7188
//		3816
//		7180
//		7178
//		9943
//		9942
//		7181
//		9700
//		3816
//		5915
//		6497
//		8814
//		7078
//		8043
//		7272
//		446
//		1468
//		326
//		4393
//		7275
//		7287
//		3632
//		8692
//		8600
//		6938
//		6431
//		6251
//		7857
//		308
//		1433
//		8284
//		6230
//		3127
//		6278
//		8956
//		3397
//		8662
//		4582
//		4832
//		4404
//		8401
//		8015
//		858
//		8228
//		5972
//		6444
//		2626
//		496
//		585
//		3915
//		6800
//		4112
//		6373
//		5685
//		5753
//		943
//		3928
//		7934
//		6822
//		3165
//		8713
//		1221
//		9020
//		8820
//		3125
//		8683
//		5405
//		4073
//		774
//		490
//		7668
//		5476
//		8585
//		4771
//		8772
//		6386
//		4212
//		6463
//		5144
//		7441
//		3918
//		7669
//		7377
//		5240
//		4189
//		66
//		5244
//		8311
//		5903
//		5374
//		6392
//		5089
//		8651
//		3004
//		3502
//		5713
//		4994
//		2836
//		1290
//		2953
//		7118
//		7132
//		1793
//		766
//		5479
//		8676
//		1151
//		1191
//		4275
//		5527
//		549
//		542
//		995
//		7581
//		4706
//		499
//		834
//		8194
//		5063
//		5246
		
		Query qAlteracoes = em.createNativeQuery("SELECT *  FROM `alteracoes_contrato` WHERE `TIPO` LIKE 'ALTERAÇÃO DE PLANO' AND `DATA_ALTERACAO` >= '2020-06-23'", AlterarcoesContrato.class);
		List<AlterarcoesContrato> resultAlteracoes = qAlteracoes.getResultList();
		
		em.getTransaction().begin();
		for (AlterarcoesContrato alterarcoesContrato : resultAlteracoes) {
			System.out.println(alterarcoesContrato.getContrato().getId().toString());
			
			List<ContasReceber> boletos = ContasReceberDAO.getBoletoPorContratoNaoVencidos(alterarcoesContrato.getContrato().getId());
			for (ContasReceber boleto: boletos) {
				boleto.setRemessa_recebida_banco(null);
				boleto.setRemessaEnviada(null);
				
				em.merge(boleto);
			}			
		}
		em.getTransaction().commit();
	}
}

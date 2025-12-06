package com.commons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.entity.Product;

/**
 * Clase de soporte de servicio encargada de la conversión de estructuras
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Service
public class MapperProduct {
	
	/**
	 * Convierte una lista de entidades de productos a una lista de productos como objetos de transferencia
	 * @param products lista de entidades de productos
	 * @return Una lista de objetos de transferencia
	 */
	public List<DtoProductListOut> fromProductList(List<Product> products){
		List<DtoProductListOut> list = new ArrayList<>();
		for(Product product: products) {
			list.add(new DtoProductListOut(
					product.getProduct_id(),
					product.getGtin(),
					product.getProduct(),
					product.getPrice(),
					product.getStatus()
					));
		}
		return list;
	}

	/**
	 * Convierte una lista de productos como objetos de transferencia a una lista de entidades de productos
	 * @param products lista de productos como objetos de transferencia
	 * @return Una lista de entidades de productos
	 */
	public Product fromDto(DtoProductIn dto) {
		Product product = new Product();
		product.setGtin(dto.getGtin());
		product.setProduct(dto.getProduct());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setCategory_id(dto.getCategory_id());
		product.setStatus(1);
        
        return product;
	}
	
	/**
	 * Crea una entidad producto desde un productos como objetos de transferencia
	 * @param id identificador a establecer al producto
	 * @param dto producto como objeto de transferencia
	 * @return Una entidad producto con la información brindada
	 */
	public Product fromDto(Integer id, DtoProductIn dto) {
		Product product = fromDto(dto);
		product.setProduct_id(id);
		return product;
	}
	
}

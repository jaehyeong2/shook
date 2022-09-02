package jjfactory.shook.busniess.domain.product;

import jjfactory.shook.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class ProductImage extends BaseTimeEntity {
}

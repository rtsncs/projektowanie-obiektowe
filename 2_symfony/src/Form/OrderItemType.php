<?php

namespace App\Form;

use App\Entity\Order;
use App\Entity\OrderItem;
use App\Entity\Product;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class OrderItemType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('quantity')
            ->add('orderId', EntityType::class, [
                'class' => Order::class,
                'choice_label' => 'id',
            ])
            ->add('productId', EntityType::class, [
                'class' => Product::class,
                'choice_label' => 'id',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => OrderItem::class,
        ]);
    }
}

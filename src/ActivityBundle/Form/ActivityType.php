<?php

namespace ActivityBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;

class ActivityType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('name')->add('description')->add('dateStart' ,DateTimeType::class, array(
            'widget' => 'choice', 'years' => range(date('Y'),date('Y')+50),
            'label' => 'Date de Debut', 'required' => true))->add('dateEnd',DateTimeType::class, array(
            'widget' => 'choice', 'years' => range(date('Y'),date('Y')+50),
            'label' => 'Date de Debut', 'required' => true))->add('file');
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ActivityBundle\Entity\Activity'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'activitybundle_activity';
    }


}

<?php

namespace RestoNurseryBundle\Controller;

use RestoNurseryBundle\Entity\SaveResto;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class submobileController extends Controller
{

    public function newAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $save=new SaveResto();
        //$save->setId($request->get('$id'));
        $save->setDateBegin($request->get('$date_begin'));
        $save->setDateEnd($request->get('$date_end'));
        $save->setIdChild($request->get('Child_id'));

        $em->persist($save);
        $em->flush();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($save);
        return new JsonResponse($formatted);

    }

}

<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SaveResto
 *
 * @ORM\Table(name="save_resto")
 * @ORM\Entity
 */
class SaveResto
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="date_begin", type="string", length=10, nullable=false)
     */
    private $dateBegin;

    /**
     * @var string
     *
     * @ORM\Column(name="date_end", type="string", length=10, nullable=false)
     */
    private $dateEnd;


}

